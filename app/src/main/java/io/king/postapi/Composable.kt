package io.king.postapi

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Forms(viewModel: MainActivityViewModel) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()


    var fullName by rememberSaveable { mutableStateOf("") }
    var status by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }

    var validateFullName by rememberSaveable {mutableStateOf(true)}
    var validateStatus by rememberSaveable {mutableStateOf(true)}
    var validateGender by rememberSaveable {mutableStateOf(true)}
    var validateEmail by rememberSaveable {mutableStateOf(true)}

    val validateFullNameError = "Please input a valid name"
    val validateStatusError = "Please input a surname"
    val validateEmailError = "E-mail format incorrect"
    val validateGenderError = "phone number format incorrect"

    fun validateData(fullName: String, status: String, email: String, gender: String): Boolean {

        validateFullName = fullName.isNotBlank()
        validateStatus = status.isNotBlank()
        validateEmail = email.isNotBlank()
        validateGender = gender.isNotBlank()


        return validateFullName && validateStatus && validateEmail && validateGender
    }

    fun register(
        fullName: String,
        status: String,
        email: String,
        gender: String,
    ){
        if (validateData(fullName,status,email,gender)){
            // registration Logic

            Log.d("Register Detail", "FullName: $fullName, Status: $status, Email: $email, Gender: $gender")
            val user = User(id = "", fullName, email, status, gender)
            viewModel.createNewUser(user)
        }else {
            Toast.makeText(context, "Please review fields", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize().padding(horizontal = 20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(vertical = 20.dp),
            color = Color.Blue
        )

        CustomOutlinedTextField(
            value = fullName,
            onValueChange = {fullName = it},
            painter = R.drawable.perm_identity,
            showError = !validateFullName,
            label = "Full Name",
            errorMessage = validateFullNameError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )

        CustomOutlinedTextField(
            value = email,
            onValueChange = {email = it},
            painter = R.drawable.alternate_email,
            showError = !validateEmail,
            label = "Email Address",
            errorMessage = validateEmailError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )

        CustomOutlinedTextField(
            value = status,
            onValueChange = {status = it},
            painter = R.drawable.switched,
            showError = !validateStatus,
            label = "Marital Status",
            errorMessage = validateStatusError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )

        CustomOutlinedTextField(
            value = gender,
            onValueChange = {gender = it},
            painter = R.drawable.gender,
            showError = !validateGender,
            label = "Gender",
            errorMessage = validateGenderError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )


        Button(
            onClick = {
                register(fullName, email, gender, status)
                Log.d("userdetail", fullName)

            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)
        ) {
            Text(
                text = "Register", fontSize = 20.sp
            )
        }
    }
}
