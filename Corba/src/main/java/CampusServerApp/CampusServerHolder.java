package CampusServerApp;

/**
* Corba/CampusServerApp/CampusServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 29, 2021 3:13:20 o'clock PM EDT
*/

public final class CampusServerHolder implements org.omg.CORBA.portable.Streamable
{
  public CampusServerApp.CampusServer value = null;

  public CampusServerHolder ()
  {
  }

  public CampusServerHolder (CampusServerApp.CampusServer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CampusServerApp.CampusServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CampusServerApp.CampusServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CampusServerApp.CampusServerHelper.type ();
  }

}
