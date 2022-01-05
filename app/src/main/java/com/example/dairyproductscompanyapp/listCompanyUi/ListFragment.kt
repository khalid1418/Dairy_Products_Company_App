package com.example.dairyproductscompanyapp.listCompanyUi

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dairyproductscompanyapp.R
import com.example.dairyproductscompanyapp.databinding.FragmentListBinding
import com.example.dairyproductscompanyapp.model.CompanyDataModel
import com.example.dairyproductscompanyapp.utils.ViewModelFactory
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import javax.security.auth.Destroyable


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding

    private val viewModel: ListCompanyViewModel by activityViewModels {
        ViewModelFactory()
    }

    private var isSignIn = true
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
        if (result.resultCode == Activity.RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
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
            Log.e("TAG","id:${it.userid}")
            val action = ListFragmentDirections.actionListFragmentToDetailCompanyFragment(it.nameCompany , it.phone , it.nameProduct , it.price , it.image , it.userid)
            this.findNavController().navigate(action)
        }

        binding?.recyclerView?.adapter = adapter
//        viewModel.company.value.let {
//            adapter.submitList(it)
//
//        }
//        binding?.recyclerView?.adapter = adapter
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
//
//                viewModel.company.collect {
//                    adapter.submitList(it)
//                }
//            }
//        }
        viewModel.companyLiveData.observe(viewLifecycleOwner, {
            it.let {
                adapter.submitList(it)
            }
        })


        val auth = Firebase.auth
        binding?.addButton?.setOnClickListener {
            checkUserSignIn()


            Log.e("TAG", "www:${viewModel.company.value}")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
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
                        Toast.makeText(context, "LogOut", Toast.LENGTH_SHORT).show()
                    }
                isSignIn = true
            }
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (isSignIn) {
            menu.findItem(R.id.sign_in).isVisible = true
            menu.findItem(R.id.sign_out).isVisible = false
        } else {
            menu.findItem(R.id.sign_out).isVisible = true
            menu.findItem(R.id.sign_in).isVisible = false
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = Firebase.auth.currentUser
        if (currentUser != null) {
            isSignIn = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view
        _binding = null
    }


}