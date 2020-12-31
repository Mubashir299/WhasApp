 package com.example.whasapp.View.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.whasapp.R;
import com.example.whasapp.View.MainActivity;
import com.example.whasapp.databinding.ActivityPhoneLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

 public class PhoneLoginActivity extends AppCompatActivity  {

  private ActivityPhoneLoginBinding binding;
    private static String TAG = "PhoneLoginActivitytest";

    private FirebaseAuth mAuth;
    private String mVerificationId;
     private PhoneAuthProvider.ForceResendingToken mResendToken;
     private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
     private ProgressDialog progressDialog;

     private FirebaseUser firebaseUser;
     private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
       binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_login);





     mAuth = FirebaseAuth.getInstance();
    // firestore = FirebaseFirestore.getInstance();

    /* firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
     if (firebaseUser!=null){
      //startActivity(new Intent(this,SetUserInfoActivity.class));
     }
*/     progressDialog = new ProgressDialog(this);

     binding.btnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
       Log.d("testbtn",binding.btnNext.getText().toString());
       if (binding.btnNext.getText().toString().equals("Next")) {
           progressDialog.setMessage( "Please wait..." );
           progressDialog.show();
        String phone = "+" + binding.edCodeCountry.getText().toString() + binding.edPhone.getText().toString();
        startPhoneNumberVerification(phone);
       } else {
      /*  progressDialog.setMessage("Verifying ..");
        progressDialog.show();*/
       verifyPhoneNumberWithCode(mVerificationId, binding.edCode.getText().toString());
       }
      }
     });

   /*  mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


      @Override
      public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
       Log.d(TAG, "onVerificationCompleted: Complete");
       signInWithPhoneAuthCredential(phoneAuthCredential);
       progressDialog.dismiss();

       String code = phoneAuthCredential.getSmsCode();

       //sometime the code is not detected automatically
       //in this case the code will be null
       //so user has to manually enter the code
       if (code != null) {
        binding.edCode.setText(code);
        //verifying the code
        verifyVerificationCode(code);
       }
      }



      @Override
      public void onVerificationFailed(@NonNull FirebaseException e) {
       Log.d(TAG, "onVerificationFailed: " + e.getMessage());
      }


      @Override
      public void onCodeSent(@NonNull String verificationId,
                             @NonNull PhoneAuthProvider.ForceResendingToken token) {

       Log.d(TAG, "onCodeSent:" + verificationId);


       mVerificationId = verificationId;
       mResendToken = token;

       binding.btnNext.setText("Confirm");
       binding.edCode.setVisibility(View.VISIBLE);
       binding.edCodeCountry.setEnabled(false);
       binding.edPhone.setEnabled(false);

       progressDialog.dismiss();

      }
     };*/
    }

  private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks2 = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
   @Override
   public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
    //Getting the code sent by SMS
    String code = phoneAuthCredential.getSmsCode();

    //sometime the code is not detected automatically
    //in this case the code will be null
    //so user has to manually enter the code
    if (code != null) {
     binding.edCode.setText(code);
     //verifying the code
     verifyVerificationCode(code);
    }
   }

   @Override
   public void onVerificationFailed(FirebaseException e) {
    progressDialog.dismiss();
    Toast.makeText(PhoneLoginActivity.this, "not Verify", Toast.LENGTH_LONG).show();
   }

   @Override
   public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
    super.onCodeSent(s, forceResendingToken);
    mVerificationId = s;
    progressDialog.dismiss();
    binding.btnNext.setText("Confirm");
    binding.edCode.setVisibility(View.VISIBLE);
    binding.edCodeCountry.setEnabled(false);
    binding.edPhone.setEnabled(false);
   }
  };
  PhoneAuthCredential credential;
  private void verifyVerificationCode(String otp) {
   if(!mVerificationId.isEmpty() && mVerificationId != null)
    //creating the credential
    credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
   else {
    Toast.makeText(PhoneLoginActivity.this, "Wrong Code", Toast.LENGTH_LONG).show();

    //next.setEnabled(true);
   }

   //signing the user
   if(credential != null)
    signInWithPhoneAuthCredential(credential);

  }
  private void startPhoneNumberVerification(String phoneNumber) {

   progressDialog.setMessage("Send code to : "+phoneNumber);
   progressDialog.show();
   PhoneAuthOptions options =
           PhoneAuthOptions.newBuilder(mAuth)
                   .setPhoneNumber(phoneNumber)       // Phone number to verify
                   .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                   .setActivity(this)                 // Activity (for callback binding)
                   .setCallbacks(mCallbacks2)          // OnVerificationStateChangedCallbacks
                   .build();
   PhoneAuthProvider.verifyPhoneNumber(options);        // OnVerificationStateChangedCallbacks

  }

  private void verifyPhoneNumberWithCode(String verificationId, String code) {

   PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
   signInWithPhoneAuthCredential(credential);
   progressDialog.setMessage("Verifying Number");
  }

  private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
   mAuth.signInWithCredential(credential)
           .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()) {

              progressDialog.dismiss();
              Log.d(TAG, "signInWithCredential:success");
             // FirebaseUser user = task.getResult().getUser();
              startActivity(new Intent(PhoneLoginActivity.this, MainActivity.class));

             } else {

              progressDialog.dismiss();
              Log.w(TAG, "signInWithCredential:failure", task.getException());
              if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

               Log.d(TAG, "onComplete: Error Code");

              }
             }
            }
           });
  }
 }