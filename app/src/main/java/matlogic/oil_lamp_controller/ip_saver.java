package matlogic.oil_lamp_controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ip_saver extends AppCompatActivity {

    private EditText ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_saver);

        Button saveIp = (Button) findViewById(R.id.button);
        ip = (EditText) findViewById(R.id.plain_text_input);

        saveIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip_address = ip.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("ip_address", ip_address);
                startActivity(intent);
            }
        });
    }
}