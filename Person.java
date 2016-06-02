


public class Person
{

    String name;
    String services;
    double value;


    public Person (String name,String services,double value)
    {
        this.name = name;
        this.services = services;
        this.value = value;
    }



    public Person (String name, double value)
    {
        this.name = name;
        this.value = value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public String toString()
    {
        if (this.value != 0)
        {
            return this.name + ": " + Math.round((this.value)*100.0)/100.0 + "$";
        }

        if (this.services == null)
        {
            return this.name + ": " + this.value + "$";
        }

        return this.name + ": " + this.services + " - " + this.value + "$";

    }


}
