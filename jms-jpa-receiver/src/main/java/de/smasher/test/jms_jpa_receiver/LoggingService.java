package de.smasher.test.jms_jpa_receiver;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(mappedName = "jms/logging")
public class LoggingService implements MessageListener {

    @PersistenceContext(unitName = "logging-unit")
    private EntityManager entityManager;
    
    @Override
    public void onMessage(Message message)
    {
        try
        {
            TextMessage textMessage = (TextMessage) message;
            entityManager.persist(new LogMessage(textMessage.getText()));
        }
        catch (JMSException e)
        {
            throw new IllegalStateException(e);
        }
    }
}