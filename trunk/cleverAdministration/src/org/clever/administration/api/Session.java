package org.clever.administration.api;


/**
 * Classe che permette di invocare le API
 * @author maurizio
 *
 */
final public class Session {
    
    final AdministrationModule module;
    final HostAdministrationModule hostAdministrationModule;
    final VMAdministrationModule vMAdministrationModule;
    
    
    final Settings settings;

    public Settings getSettings() {
        return settings;
    }
    
    //per ora faccio un meccanismo molto spartano non considerando il modulo passato
    public Session(Settings s)
    {
        settings = s;
        module = new AdministrationModule(this);
        hostAdministrationModule = new HostAdministrationModule(this);
        vMAdministrationModule = new VMAdministrationModule(this);
    }
    
    /**
     * Ritorna l'administrationmodule per invocare le funzionalita' 
     * per ora ritorna un solo modulo ignorando il nome passato. Si potra' invocare un metodo generico di un agente di un'entita' (CM o HM), passandogli i parametri
     * @param moduleName
     * @return 
     */
    public AdministrationModule getModule(String moduleName)
    {
        return module;
        
    }

    
    /**
     * Ritorna un HostAdministrationModule per gestirele entita' clever a livello di host
     * per es.: getActiveCM, listHostManagers,ecc.
     * @return 
     */
    public HostAdministrationModule getHostAdministrationModule() {
        return hostAdministrationModule;
    }
    
    /**
     * Ritorna un VMAdministrationModule per gestire le VM
     * 
     * @return 
     */
    public VMAdministrationModule getVMAdministrationModule() {
        return vMAdministrationModule;
    }
    
    
}