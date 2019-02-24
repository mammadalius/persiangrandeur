package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class I010_SoftwareHardwareInformationActivity extends BaseActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		getListView().setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				if (position == 0)
				{
					Intent intent = new Intent(getApplicationContext(), I10_1SoftwareInformationActivity.class);
					intent.putExtra("resID", R.array.Software_Information);
					startActivity(intent);
				}
				else if (position == 1)
				{
					Intent intent = new Intent(getApplicationContext(), I10_2HardwareInformationActivity.class);
					intent.putExtra("resID", R.array.Hardware_Information);
					startActivity(intent);
				}
			}
		});
	}
}
