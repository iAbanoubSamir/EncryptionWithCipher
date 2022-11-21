package com.abanoub.cipher.ui.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.abanoub.cipher.ui.theme.EncryptionWithCipherTheme
import com.abanoub.cipher.utils.CryptoManager

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cryptoManager = CryptoManager()

        setContent {
            EncryptionWithCipherTheme {

            }
        }

    }
}
