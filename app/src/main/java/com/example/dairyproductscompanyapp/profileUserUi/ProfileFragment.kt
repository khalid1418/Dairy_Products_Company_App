package com.example.dairyproductscompanyapp.profileUserUi

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.databinding.FragmentProfileBinding
import com.example.dairyproductscompanyapp.model.OrderDataModel
import com.example.dairyproductscompanyapp.model.UserProfile
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private val viewModel: UserProfileViewModel by activityViewModels {
        ViewModelFactory()
    }

    private fun edit(){
        if (Firebase.auth.currentUser?.uid == null || Firebase.auth.currentUser?.uid == ""){

        }else{
        val dataModel = viewModel.userInfoLiveData.value?.imageView
        val action = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(
            binding?.nameProfile?.text.toString(),
            binding?.emailProfile?.text.toString(),
            binding?.dateBirth?.text.toString(),
            dataModel.toString()
        )
        findNavController().navigate(action)
    }}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit_menu -> edit()
        }
        return true
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Firebase.auth.currentUser?.uid == null || Firebase.auth.currentUser?.uid == "") {

        } else {

            viewModel.userInfoLiveData.observe(viewLifecycleOwner, {
                binding?.nameProfile?.text = it.userName
                binding?.emailProfile?.text = it.userEmail
                binding?.dateBirth?.text = it.userBirth
                Glide.with(binding!!.profileImage)
                    .load(it.imageView).placeholder(R.drawable.loading_animation)
                    .circleCrop()
                    .error(R.drawable.ic_baseline_account_circle_24)
                    .into(binding!!.profileImage)

                Log.e("TAG", "onViewCreated:${it.userEmail} ")

            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
