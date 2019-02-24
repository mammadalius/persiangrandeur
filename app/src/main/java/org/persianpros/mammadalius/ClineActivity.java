package org.persianpros.mammadalius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ClineActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c_line);
		this.setTitle(getIntent().getStringExtra("title"));

		findViewById(R.id.btnOkCline).setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{
				if (((EditText)findViewById(R.id.etCline1)).getText().toString().length() < 2)
					Toast.makeText(getApplicationContext(), "C-line 1 must contain a path", Toast.LENGTH_LONG).show();
				else
				{
					String[] lines = new String[5];
					lines[0] = ((EditText)findViewById(R.id.etCline1)).getText().toString();
					lines[1] = ((EditText)findViewById(R.id.etCline2)).getText().toString();
					lines[2] = ((EditText)findViewById(R.id.etCline3)).getText().toString();
					lines[3] = ((EditText)findViewById(R.id.etCline4)).getText().toString();
					lines[4] = ((EditText)findViewById(R.id.etCline5)).getText().toString();
					Intent intent = getIntent();
					intent.putExtra("lines", lines);
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});
	}

}