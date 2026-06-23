package org.example;

public class Tester extends Usuario{

    private String rol;

    public Tester(String name, String lastName, String email, String country, String password, String rol){
        super(name, lastName, email, country, password);
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public void cambiarContrasena(String nuevaContrasena, Usuario usuarioObjetivo){
        if (usuarioObjetivo != null && !usuarioObjetivo.getEmail().equals(this.getEmail())) {
            throw new SecurityException("Un Tester no tiene permisos para cambiar la contraseña de otros usuarios.");
        }

        this.setPassword(nuevaContrasena);
        System.out.println("Tester (" + this.getName() + " " + this.getLastName() + ") cambió su propia contraseña con éxito.");
    }

    @Override
    public String toString(){
        return this.getName() + " " + this.getLastName() +  ", " + this.getEmail()
                + ", " + this.getCountry() + ", " + this.getRol() + ".";
    }
}
