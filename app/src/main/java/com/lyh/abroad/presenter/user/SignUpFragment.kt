package com.lyh.abroad.presenter.user


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentSignUpBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.place.PlaceFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    companion object {
        private const val GALLERY_PICK = 1000
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        add_profile.setOnClickListener {
            startIntentForProfilePick()
        }
        search_nation.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PlaceFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_PICK) {
            if (resultCode == RESULT_OK) {
                binding.signViewModel?.setProfileUri(data?.data)
            }
        }
    }

    private fun setUpBinding() {
        binding = FragmentSignUpBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            signViewModel = activityViewModels<SignViewModel>(ViewModelFactory::get).value
        }
    }

    private fun startIntentForProfilePick() {
        //TODO 커스텀 구현? 라이브러리 활용?
        Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }.let {
            startActivityForResult(it, GALLERY_PICK)
        }
    }
}
