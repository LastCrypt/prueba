package dreamhut;

import javax.swing.JOptionPane;



public class List {
    private Node first;

    public List() {
    }
    
    public boolean isEmpty()
    {
        return first==null;
    }
    
    public void AddFirst(Object obj)
    {
        if(isEmpty())
            first = new Node(obj);
        else
        {
            Node n = new Node(obj);
            n.setLink(first);
            first = n;
        }
    }
    
    @Override
    public String toString()
    {
        String message="";
        Node aux =first;
        while(aux!=null)
        {
            message = message + aux.getData() + "\n";
            aux = aux.getLink();
        }
        return message;
    }
   
    public Node Last()
    {
        Node aux =first;
        while(aux!= null && aux.getLink()!=null)
            aux = aux.getLink();
        JOptionPane.showMessageDialog(null, "holi");
        return aux;
    }
    
    public void AddLast(Object obj)
    {
        Node last = Last();
        if(last==null)
            AddFirst(obj);
        else
            last.setLink(new Node(obj));
    }
    
    public Node Previous(Node search)
    {
        Node aux=first;
        while(aux!=null && aux.getLink()!= search)
            aux=aux.getLink();
        
        return aux;
    }
    
    public void Add(Object obj)
    {
        if(isEmpty())
            AddFirst(obj);
        else
        {
            Node aux=first;
            while(aux!=null  &&  ((String)obj).compareTo((String)aux.getData())>0)
                aux=aux.getLink();
            
            if(aux==null)
                AddLast(obj);
            else
            {
                if(aux==first)
                    AddFirst(obj);
                else
                {
                    Node previous = Previous(aux);
                    Node n = new Node(obj);
                    n.setLink(aux);
                    previous.setLink(n);
                }
            }
        }

    }
    
    public boolean RemoveFirst()
    {
        if(!isEmpty())
        {
            Node aux = first;
            first = first.getLink();
            aux=null;
            return true;
        }
        return false;
    }
    
    public boolean RemoveLast()
    {
        if(!isEmpty())
        {
            Node last = Last();
            if(last==first)
                first=null;
            else
            {
                Node previous = Previous(last);
                previous.setLink(null);
                last=null;                
            }
            return true;
        }
        return false;
    }
    
    public boolean Remove (int number, int tower)
    {
        if(!isEmpty())
        {
            Node aux = first;
            while(aux!= null &&
               (((Apartment)aux.getData()).getNumber() != number ||
                ((Apartment)aux.getData()).getTower() != tower))
                          aux = aux.getLink();
            
            if(aux== null)
                return false;
            else
            {
                if(aux==first)
                    RemoveFirst();
                else
                {
                    Node previous = Previous(aux);
                    previous.setLink(aux.getLink());
                    aux = null;
                }
                return true;
            }
        }
        return false;
    }
    
    public Apartment Search(int number, int tower)
    {
        Node aux=first;
        while(aux!= null &&
               (((Apartment)aux.getData()).getNumber() != number ||
                ((Apartment)aux.getData()).getTower() != tower))
            aux=aux.getLink();
        
        if (aux == null)
            return null;
        else
            return (Apartment)aux.getData();
    }
    
    public int Size()
    {
        int count=0;
        Node aux=first;
        while(aux!=null)
        {
            count++;
            aux = aux.getLink();
        }
        return count;
    }
    public List Available()
    {
        List available = new List();
        Node aux = first;
        while(aux != null)
        {
            if(((Apartment)aux.getData()).getOwner() == null)
            {
                available.AddLast(aux.getData());
            }
            aux=aux.getLink();
        }
        return available;
    }
    
    public Owner SearchOwner(String id)
    {
        Node aux = first;
        while(aux != null && !((Owner)aux.getData()).getId().equals(id))
        {
            aux=aux.getLink();
        }
        if (aux==null)
        {
            return null;
        }
        else
        {
            return (Owner)aux.getData();
        }
    }
    public boolean Award(Owner o, int number, int tower)
    {
        Node aux = first;
        boolean ban =true;
        while (aux!=null && ban)
        {
            if(((Apartment)(aux.getData())).getNumber()==number && ((Apartment)(aux.getData())).getTower()==tower)
            {
                ban=false;
            }
            else
            {
                aux=aux.getLink();
            }
        }
        if (aux!=null && ((Apartment)(aux.getData())).getOwner()==null)
        {
            ((Apartment)(aux.getData())).setOwner(o);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Node getFirst() {
        return first;
    }
    
}
