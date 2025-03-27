package com.example.btvnfirebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.btvnfirebase.ui.theme.BtvnfirebaseTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private var user by mutableStateOf<FirebaseUser?>(null)

    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Google sign-in failed", e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build())

        user = auth.currentUser

        setContent {
            BtvnfirebaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GoogleSignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignInClick = { signInLauncher.launch(googleSignInClient.signInIntent) },
                        user = user
                    )
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser
                    Log.d("GoogleSignIn", "User: ${user?.displayName}, Email: ${user?.email}")
                } else {
                    Log.e("GoogleSignIn", "Authentication Failed", task.exception)
                }
            }
    }
}

@Composable
fun GoogleSignInScreen(modifier: Modifier = Modifier, onSignInClick: () -> Unit, user: FirebaseUser?) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user == null) {
            Button(
                onClick = onSignInClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Sign in with Google", color = MaterialTheme.colorScheme.onPrimary)
            }
        } else {
            Text(text = "Đăng nhập thành công!", modifier = Modifier.padding(16.dp))
            Text(text = "Tên: ${user.displayName}", modifier = Modifier.padding(8.dp))
            Text(text = "Email: ${user.email}", modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GoogleSignInPreview() {
    BtvnfirebaseTheme {
        GoogleSignInScreen(onSignInClick = {}, user = null)
    }
}
