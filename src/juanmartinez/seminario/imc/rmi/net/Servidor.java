/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juanmartinez.seminario.imc.rmi.net;


import java.io.IOException;
import juanmartinez.seminario.imc.rmi.lib.IRemotaCalculoImc;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Server;
/**
 *
 * @author 57301
 */
public class Servidor {
    private int puerto = 9007;
    private CallHandler invocador;
    private Server servidor;
    private CalculoRmiImcImplem calculoImc;

    public Servidor() {
        invocador = new CallHandler();
        servidor = new Server();
        calculoImc = new CalculoRmiImcImplem();
    }

    public void iniciar() throws Exception {
        try {
            invocador.registerGlobal(IRemotaCalculoImc.class, calculoImc);
            servidor.bind(puerto, invocador);
            System.out.println("Servidor LipeRMI iniciado en el puerto " + puerto);
        } catch (LipeRMIException ex) {
            throw new Exception("Error: No es posible invocar métodos remotos");
        } catch (IOException ex) {
            throw new Exception("Error: I/O " + ex.getMessage());
        }
    }

    public void detener() {
        servidor.close();
    }
}
