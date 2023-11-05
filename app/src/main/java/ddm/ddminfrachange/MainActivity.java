package ddm.ddminfrachange;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import Fragment.NovaSolicitacaoFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem((R.id.nav_home));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (id == R.id.nav_addchamado) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NovaSolicitacaoFragment(getSupportFragmentManager())).commit();
        } else if (id == R.id.nav_chamadosabertos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SolicitacoesAbertasFragment()).commit();
        } else if (id == R.id.nav_todoschamados) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoricoSolicitacoesFragment()).commit();
        } else if (id == R.id.nav_minhacidade) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MeuMunicipioFragment()).commit();
        } else if (id == R.id.nav_editarcadastro) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditarUsuarioFragment()).commit();
        } else if (id == R.id.nav_sair) {
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}