package Corba.CampusServerApp;

/**
* Corba/CampusServerApp/TimeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 7:27:23 o'clock PM EDT
*/

public final class TimeHolder implements org.omg.CORBA.portable.Streamable
{
  public Corba.CampusServerApp.Time value = null;

  public TimeHolder ()
  {
  }

  public TimeHolder (Corba.CampusServerApp.Time initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Corba.CampusServerApp.TimeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Corba.CampusServerApp.TimeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Corba.CampusServerApp.TimeHelper.type ();
  }

}
