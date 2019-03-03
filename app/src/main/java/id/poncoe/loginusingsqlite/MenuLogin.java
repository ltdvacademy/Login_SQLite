package id.poncoe.loginusingsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuLogin extends Activity {
    Button btnMasuuk, btnDaftarr;
    LoginDatabaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);

        // SQLite Database
        loginDataBaseAdapter = new LoginDatabaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Buttons
        btnMasuuk = findViewById(R.id.btnMasuk);
        btnDaftarr = findViewById(R.id.btnDaftar);

        // Set OnClick Listener on SignUp button
        btnDaftarr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                // / Create Intent for SignUpActivity and Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(),
                        SignupActivity.class);
                startActivity(intentSignUP);
            }
        });
    }

    // Methos to handleClick Event of Sign In Button
    public void signIn(View V) {
        final Dialog dialog = new Dialog(MenuLogin.this);
        dialog.setContentView(R.layout.masuk);
        dialog.setTitle("Masuk");

        // get the Refferences of views
        final EditText ubahNamaPengguna = dialog
                .findViewById(R.id.editTextUserNameToLogin);
        final EditText ubahKataSandi = dialog
                .findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = ubahNamaPengguna.getText().toString();
                String password = ubahKataSandi.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter
                        .getSinlgeEntry(userName);

                // check if the Stored password matches with Password entered by
                // user
                if (userName.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Mohon Diisi Nama Pengguna dan Sandi!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.equals(storedPassword)) {
                    startActivity(new Intent(MenuLogin.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(MenuLogin.this,
                            "Nama Pengguna atau Kata Sandi tidak Sesuai!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }



    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        keluar();
    }

    private void keluar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Yakin ingin Keluar?")
                .setCancelable(false)
                // tidak bisa tekan tombol back
                // jika pilih yess
                .setPositiveButton("Iya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // this.finish();

                                MenuLogin.this.finish();
                            }

                            private void finish() {
                                // TODO Auto-generated method stub

                            }
                        })
                // jika pilih no
                .setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }
}