package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import org.systers.mentorship.R
import org.systers.mentorship.databinding.ActivitySettingsBinding
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.ChangePasswordFragment


class SettingsActivity : BaseActivity() {

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private lateinit var settingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding.root)



        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        settingsBinding.apply {
            tvLogout.setOnClickListener {
                val builder = AlertDialog.Builder(this@SettingsActivity)
                builder.setTitle(R.string.confirm_logout)
                builder.setMessage(R.string.confirm_logout_msg)
                builder.setPositiveButton(R.string.logout) { _, _ ->
                    preferenceManager.clear()
                    startActivity(Intent(this@SettingsActivity, LoginActivity::class.java))
                    finishAffinity()
                }
                builder.setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.cancel()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

            tvResetPassword.setOnClickListener {
                ChangePasswordFragment.newInstance().show(supportFragmentManager, null)
            }

            tvAbout.setOnClickListener {
                startActivity(Intent(baseContext, AboutActivity::class.java))
            }

            tvFeedback.setOnClickListener {
                startActivity(Intent(baseContext, FeedbackActivity::class.java))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
