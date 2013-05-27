/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clever.administration.common;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.clever.Common.XMPPCommunicator.ConnectionXMPP;

/**
 *
 * @author maurizio
 */
class SimpleCleverCommandClientProvider implements CleverCommandClientProvider {
    
    private  String username;
    private  String password;
    private  String servername;
    private  Integer port;
    private  String nickname;
    private  String room;
    
    
     private final CleverCommandClient client;
    
    
    
    //private final CleverCommandClient admintools;
    
    private static final Logger log = Logger.getLogger(SimpleCleverCommandClientProvider.class);
    
    
    
    public SimpleCleverCommandClientProvider() {
        client = new CleverCommandClient();
    }
    
    //costruttore a cui si passano delle properties con i parametri di configurazione (servername, password,ecc.)
    public SimpleCleverCommandClientProvider(Properties properties) {
        this();
        //TODO: validate the properties
       this.configure(properties);
        
        
        
    }

    
    /**
     * Semplicissima politica: si connette alla prima invocazione 
     * e mantiene una sola connessione.
     * 
     * @return
     * Il client se va a buon fine o null in caso di errore
     */
    
    
    @Override
    public synchronized CleverCommandClient getClient() {
        
        
        if(client.isActive())
        {
            return client;
        }
        
        //mi connetto
        
        client.connect(servername, username, password, port, room, nickname);
        //TODO: lanciare eccezione se la connessione non e' andata a buon fine
        //controllo se la connessione e' andata a buon fine
        //if(admintools.isActive())
        //{
        //    log.error("XMPP connection error on server: " + servername + " with username " + username);
        //    return null;
        //}
        return client;
        
    }
    
    
    /**
     * Chiamato per rilasciare il Client. In questa implementazione non fa nulla
     */
    @Override
    public synchronized void releaseClient() {
        //TODO: manage connection
        return;
    }

    @Override
    final synchronized public void configure(Properties properties) {
        //TODO: validate the properties
        username = properties.getProperty(Environment.XMPP_USERNAME);
        password = properties.getProperty(Environment.XMPP_PASSWORD);
        servername = properties.getProperty(Environment.XMPP_SERVER);
        port = Integer.parseInt(properties.getProperty(Environment.XMPP_PORT));
        room = properties.getProperty(Environment.XMPP_ROOM);
        nickname = properties.getProperty(Environment.XMPP_NICKNAME);
    }
    
}