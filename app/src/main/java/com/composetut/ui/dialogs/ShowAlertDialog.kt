package com.composetut.ui.dialogs

import android.telephony.AccessNetworkConstants.UtranBand
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ShowAlertDialog(value:Boolean,callback:(Boolean)-> Unit) {
    // State to manage whether the dialog is visible
    val showDialog = remember { mutableStateOf(value) }

    // Display the AlertDialog if showDialog is true
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when tapped outside or by the back button
                showDialog.value = false
                callback.invoke(false)
            },
            title = {
                Text(text = "Dialog Title")
            },
            text = {
                Text("This is a simple AlertDialog example in Jetpack Compose.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Handle the confirmation action
                        showDialog.value = false
                        callback.invoke(false)
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Handle the dismiss action
                        showDialog.value = false
                        callback.invoke(false)
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}
