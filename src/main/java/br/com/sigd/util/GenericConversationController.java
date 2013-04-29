package br.com.sigd.util;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 * Classe modelo para ManagedBeans
 * @author Marcelo Claudio
 * @version 1.0
 */
public abstract class GenericConversationController <Class,RepositoryClass> extends GenericController<Class, RepositoryClass> {
    
    @Inject
    protected Conversation conversation;
    
    public void beginConversation() {
        if (this.conversation.isTransient()) {            
            this.conversation.begin();
        }
    }

    public void endConversation() {
        if (!this.conversation.isTransient()) {            
            this.conversation.end();
        }
    }
}
