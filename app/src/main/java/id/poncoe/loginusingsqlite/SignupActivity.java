package id.poncoe.loginusingsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity {
    EditText ubahNamaPengguna,ubahKataSandi,ubahKonfirmasiKataSandi;
    Button buatAkun;

    LoginDatabaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar);

        // Database Adapter
        loginDataBaseAdapter=new LoginDatabaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        ubahNamaPengguna= findViewById(R.id.editNamaPengguna);
        ubahKataSandi= findViewById(R.id.editKataSandi);
        ubahKonfirmasiKataSandi= findViewById(R.id.editKonfirmasiKataSandi);

        buatAkun= findViewById(R.id.btnBuatAkun);
        buatAkun.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName=ubahNamaPengguna.getText().toString();
                String password=ubahKataSandi.getText().toString();
                String confirmPassword=ubahKonfirmasiKataSandi.getText().toString();

                // periksa apakah ada yang kosong
                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Mohon Diisi Nama Pengguna dan Sandi!", Toast.LENGTH_LONG).show();
                    return;
                }
                // periksa jika kedua password sesuai
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Kata Sandi tidak sesuai!", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // menyimpan data ke database
                    loginDataBaseAdapter.insertEntry(userName, password);
                    startActivity(new Intent(SignupActivity.this, MenuLogin.class));
                }
                Toast.makeText(getApplicationContext(), "Selamat! Pembuatan Akun sudah Berhasil, Silahkan Login! ", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
