package logicaDeNegocios.gestores;

import accesoADatos.GestorBD;
import logicaDeNegocios.entidades.Empleado;

public abstract class SistemaPersonal {

	public static Empleado getEmpleado(Long numLegajo) {

		Empleado empleado = GestorBD.buscarEmpleado(numLegajo);		
		
		return empleado;
	}

}
