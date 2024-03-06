import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.juanlu.proyecto_juanlu.R
import com.juanlu.proyecto_juanlu.data.Usuario.UserPreferences
import com.juanlu.proyecto_juanlu.dataStore
import com.juanlu.proyecto_juanlu.databinding.FragmentUsuarioIntroBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioIntroFragment : Fragment() {
    private lateinit var _binding: FragmentUsuarioIntroBinding
    private val binding get() = _binding!!

    private var accion = R.id.action_usuarioIntroFragment_to_menuFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsuarioIntroBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        binding.btGuardarPreferencias.setOnClickListener() {
            findNavController().navigate(accion)
            lifecycleScope.launch(Dispatchers.IO) {
                guardarUsuario(
                    checked = binding.chkRecordar.isChecked,
                    usuario = binding.etUsuario.text.toString(),
                    descripcion = binding.etDescripcion.text.toString(),
                )
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            tomarDatos().collect {
                withContext(Dispatchers.Main) {
                    binding.etUsuario.setText(it.usuario)
                    binding.etDescripcion.setText(it.contrasena)
                    binding.chkRecordar.isChecked = it.checked
                }
            }
        }


        return binding.root
    }

    private fun tomarDatos() = binding.root.context.dataStore.data.map { preferences ->
        UserPreferences(
            usuario = preferences[stringPreferencesKey("usuario")].orEmpty(),
            contrasena = preferences[stringPreferencesKey("contrasena")].orEmpty(),
            checked = preferences[booleanPreferencesKey("checked")] ?: false

        )
    }

    private suspend fun guardarUsuario(
        usuario: String,
        checked: Boolean,
        descripcion: String,
    ) {
        context?.dataStore?.edit { preferences ->
            preferences[booleanPreferencesKey("checked")] = checked
            preferences[stringPreferencesKey("nombre")] = usuario
            preferences[stringPreferencesKey("descripcion")] = descripcion
        }
    }

}
