package Corba.CampusServerApp;


/**
* Corba/CampusServerApp/CampusHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 7:27:23 o'clock PM EDT
*/

abstract public class CampusHelper
{
  private static String  _id = "IDL:Corba/CampusServerApp/Campus:1.0";

  public static void insert (org.omg.CORBA.Any a, Corba.CampusServerApp.Campus that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Corba.CampusServerApp.Campus extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (Corba.CampusServerApp.CampusHelper.id (), "Campus", new String[] { "KKL", "WST", "DVL"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Corba.CampusServerApp.Campus read (org.omg.CORBA.portable.InputStream istream)
  {
    return Corba.CampusServerApp.Campus.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Corba.CampusServerApp.Campus value)
  {
    ostream.write_long (value.value ());
  }

}