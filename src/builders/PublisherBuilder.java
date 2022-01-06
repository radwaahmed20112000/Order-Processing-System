package builders;

import interfaces.IPublisher;
import models.Publisher;

public class PublisherBuilder {
    String name;
    String address;
    String telephoneNumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public IPublisher generatePublisher (){
        IPublisher publisher = new Publisher(this.name , this.address , this.telephoneNumber) ;
        return publisher;

    }
}
