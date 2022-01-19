package com.example.dairyproductscompanyapp.listCompanyUi

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.databinding.FragmentListBinding
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.model.UserProfile
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.NumberFormat


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding
    var menu123: Menu ?= null



    var isSignIn: Boolean = true

    private val viewModel: ListCompanyViewModel by activityViewModels {
        ViewModelFactory()
    }


    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
    )
    private val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse

        if (result.resultCode == Activity.RESULT_OK && response?.isNewUser == true) {
            val user = FirebaseAuth.getInstance().currentUser
            viewModel.addProfile(UserProfile(user?.displayName.toString(), user?.email.toString()))
            isSignIn = true

            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    private fun checkUserSignIn() {
        if (!isSignIn) {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        } else {
            Toast.makeText(requireContext(), "You not sign in", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val currentUser = Firebase.auth.currentUser

        if (currentUser == null){
            isSignIn=false

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        var adapter = CompanyListAdapter {
            Log.e("TAG", "price1:${it.price}")
            val action = ListFragmentDirections.actionListFragmentToDetailCompanyFragment(
                it.nameCompany,
                it.phone,
                it.nameProduct,
                NumberFormat.getCurrencyInstance().format(it.price),
                it.image,
                it.userid,
                it.reference
            )
            Log.e("TAG", "price1:${it.price}")

            this.findNavController().navigate(action)


        }

        binding?.recyclerView?.adapter = adapter

        viewModel.companyLiveData.observe(viewLifecycleOwner, {
            it.let {
                adapter.submitList(it)
            }
        })


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu123 = menu
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_in -> {
                signInLauncher.launch(signInIntent)

            }
            R.id.sign_out -> {

                AuthUI.getInstance()
                    .signOut(requireContext())
                    .addOnCompleteListener {
                        isSignIn = false
                        update()
                        Toast.makeText(context, "LogOut", Toast.LENGTH_SHORT).show()
                    }
                isSignIn = true
            }
//            R.id.order_buyer -> {
//                findNavController().navigate(R.id.action_listFragment_to_orderListFragment)
//            }
//            R.id.settingsFragment -> {
//                findNavController().navigate(R.id.action_listFragment_to_settingsFragment)
//            }
        }
        return true
    }




    fun update(){

        Log.e("TAG1", "onPrepareOptionsMenu: here $isSignIn")
        if (menu123 != null) {
            if (isSignIn) {
                menu123?.findItem(R.id.sign_in)?.isVisible = false
                menu123?.findItem(R.id.sign_out)?.isVisible=true
            } else {
                menu123?.findItem(R.id.sign_in)?.isVisible = true
                menu123?.findItem(R.id.sign_out)?.isVisible=false
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


//    override fun onResume() {
//        Log.e("hussain", "onResume: onResume", )
//
//        if (Firebase.auth.currentUser?.uid == null){
//            isSignIn=true
//        }else{
//            Log.e("hussain", "onResume: else", )
//            isSignIn=false
//        }
//        super.onResume()
//    }


    override fun onResume() {
        super.onResume()
        val currentUser = Firebase.auth.currentUser
        if (currentUser != null) {
            isSignIn = true
            Log.e("TAG", "onResume2:$isSignIn ", )

        }else{
            isSignIn=false
        }
        update()



        Log.e("TAG1", "onresume: $isSignIn")

    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        Log.e("hussain", "onPrepareOptionsMenu: here $isSignIn")
        if (isSignIn) {
            menu.findItem(R.id.sign_in).isVisible = false
            menu.findItem(R.id.sign_out).isVisible = true
        } else {
            menu.findItem(R.id.sign_out).isVisible = false
            menu.findItem(R.id.sign_in).isVisible = true
        }
    }
}