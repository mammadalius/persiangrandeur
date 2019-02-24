package org.persianpros.mammadalius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class VPNActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vpn);

		findViewById(R.id.btnOkVPN).setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{
				if (((EditText)findViewById(R.id.etServer)).getText().toString().length() < 1 ||
						((EditText)findViewById(R.id.etUsername)).getText().toString().length() < 1 ||
						((EditText)findViewById(R.id.etPassword)).getText().toString().length() < 1)
					Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
				else
				{
					Intent intent = getIntent();
					intent.putExtra("server", ((EditText)findViewById(R.id.etServer)).getText().toString());
					intent.putExtra("user", ((EditText)findViewById(R.id.etUsername)).getText().toString());
					intent.putExtra("pass", ((EditText)findViewById(R.id.etPassword)).getText().toString());
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});
	}
}