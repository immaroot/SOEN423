package Corba.CampusServerApp;

/**
* Corba/CampusServerApp/CampusHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 7:27:23 o'clock PM EDT
*/

public final class CampusHolder implements org.omg.CORBA.portable.Streamable
{
  public Corba.CampusServerApp.Campus value = null;

  public CampusHolder ()
  {
  }

  public CampusHolder (Corba.CampusServerApp.Campus initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Corba.CampusServerApp.CampusHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Corba.CampusServerApp.CampusHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Corba.CampusServerApp.CampusHelper.type ();
  }

}