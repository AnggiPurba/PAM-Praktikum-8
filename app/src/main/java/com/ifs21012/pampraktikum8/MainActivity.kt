package com.ifs21012.pampraktikum8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.ifs21012.pampraktikum8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupAction()
    }
    private fun setupView() {
        binding.navView.setCheckedItem(R.id.nav_home)
        binding.inAppBar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)
        loadFragment(FLAG_FRAGMENT_HOME)
    }
    private fun setupAction() {
        binding.inAppBar.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.inAppBar.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_add -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Add!")
                    true
                } R
                .id.action_settings -> {
                loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Settings!")
                true
            } R
                .id.action_logout -> {
                loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Logout!")
                true
            }
                else -> true
            }
        }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadFragment(FLAG_FRAGMENT_HOME)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                } R
                .id.nav_profile -> {
                loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Profile!")
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                true
            } R
                .id.nav_notes -> {
                loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Notes!")
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
                else -> true
            }
        }
        binding.inAppBar.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(FLAG_FRAGMENT_HOME)
                    true
                }
                R.id.navigation_dashboard -> {
                    loadFragment(FLAG_FRAGMENT_NOTIFICATION)
                    true
                }
                else -> true
            }
        }

    }
    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId =
            binding.inAppBar.inContentMain.frameContainer.id
        when (flag) {
            FLAG_FRAGMENT_HOME -> {
                binding.inAppBar.bottomNavView
                    .menu
                    .findItem(R.id.navigation_home)
                    .setChecked(true)
                val homeFragment = HomeFragment()
                val bundle = Bundle().apply {
                    this.putString(
                        HomeFragment.EXTRA_MESSAGE,
                        message ?: "Belum ada menu yang dipilih!"
                    )
                }
                homeFragment.arguments = bundle
                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        homeFragment,
                        HomeFragment::class.java.simpleName
                    )
                    .commit()
            }
            FLAG_FRAGMENT_DASHBOARD -> {
                val dashboardFragment = DashboardFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(DashboardFragment::class.java.simpleName)
                if (fragment !is DashboardFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            dashboardFragment,
                            DashboardFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
            FLAG_FRAGMENT_NOTIFICATION -> {
                val notificationFragment = NotificationFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(NotificationFragment::class.java.simpleName)
                if (fragment !is NotificationFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            notificationFragment,
                            NotificationFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
        }
    }
    companion object {
        const val FLAG_FRAGMENT_HOME = "fragment_home"
        const val FLAG_FRAGMENT_DASHBOARD = "fragment_dashboard"
        const val FLAG_FRAGMENT_NOTIFICATION = "fragment_notification"
    }
}

