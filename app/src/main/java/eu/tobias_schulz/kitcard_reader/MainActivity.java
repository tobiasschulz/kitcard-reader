package eu.tobias_schulz.kitcard_reader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.MifareClassic;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.app.PendingIntent;
import android.widget.Toast;


public class MainActivity extends Activity {

    private NfcAdapter adapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent("eu.tobias_schulz.kitcard_reader.TECH_DISCOVERED").addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        final IntentFilter intentFilter = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        intentFiltersArray = new IntentFilter[] { intentFilter };
        techListsArray = new String[][] { new String[] { MifareClassic.class.getName() } };

        NfcManager manager = (NfcManager) getSystemService(Context.NFC_SERVICE);
        adapter = manager.getDefaultAdapter();
        if (!adapter.isEnabled())
        {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.activate_nfc), Toast.LENGTH_LONG).show();
            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Debugger safeguard.
        if(adapter == null)
            return;

        adapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
    }

    @Override
    protected void onPause() {
        super.onResume();

        // Debugger safeguard.
        if(adapter == null)
            return;

        adapter.disableForegroundDispatch(this);
    }


}
