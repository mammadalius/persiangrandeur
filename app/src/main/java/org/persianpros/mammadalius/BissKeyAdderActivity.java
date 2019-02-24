package org.persianpros.mammadalius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BissKeyAdderActivity extends Activity implements OnClickListener
{
	EditText etCode;
	int type = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bisskey_adder);
		etCode = (EditText) findViewById(R.id.etCode);

		Intent intentx = getIntent();
		type = intentx.getIntExtra("type", 0);
		if (type > 0)
		{
			this.setTitle(intentx.getStringExtra("title"));
			((TextView)findViewById(R.id.tvDescription)).setText(intentx.getStringExtra("desc"));

			InputFilter[] FilterArray = new InputFilter[1]; 
			FilterArray[0] = new InputFilter.LengthFilter(4); 
			etCode.setFilters(FilterArray);

			if (type == 3 || type == 5)
				findViewById(R.id.rlWords).setVisibility(View.GONE);
		}

		findViewById(R.id.btnA).setOnClickListener(this);
		findViewById(R.id.btnB).setOnClickListener(this);
		findViewById(R.id.btnC).setOnClickListener(this);
		findViewById(R.id.btnD).setOnClickListener(this);
		findViewById(R.id.btnE).setOnClickListener(this);
		findViewById(R.id.btnF).setOnClickListener(this);
		findViewById(R.id.btnBissOK).setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		String temp;
		switch (v.getId())
		{		
		case R.id.btnA:	
			temp = etCode.getText().toString();
			etCode.setText(temp + "A");
			etCode.setSelection(etCode.length());
			break;
		case R.id.btnB:	
			temp = etCode.getText().toString();
			etCode.setText(temp + "B");
			etCode.setSelection(etCode.length());
			break;
		case R.id.btnC:	
			temp = etCode.getText().toString();
			etCode.setText(temp + "C");
			etCode.setSelection(etCode.length());
			break;
		case R.id.btnD:	
			temp = etCode.getText().toString();
			etCode.setText(temp + "D");
			etCode.setSelection(etCode.length());
			break;
		case R.id.btnE:	
			temp = etCode.getText().toString();
			etCode.setText(temp + "E");
			etCode.setSelection(etCode.length());
			break;
		case R.id.btnF:	
			temp = etCode.getText().toString();
			etCode.setText(temp + "F");
			etCode.setSelection(etCode.length());
			break;
		case R.id.btnBissOK:
			if (type == 0)
			{
				Intent intent = getIntent();
				intent.putExtra("code", etCode.getText().toString());
				setResult(RESULT_OK, intent);
				finish();
			}
			else if (type > 0 && etCode.getText().length() == 4)
			{
				Intent intent = getIntent();
				intent.putExtra("code", etCode.getText().toString());
				setResult(RESULT_OK, intent);
				finish();
			}
			else
				Toast.makeText(getApplicationContext(), "PID must contains 4 characters", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}		
	}
}
