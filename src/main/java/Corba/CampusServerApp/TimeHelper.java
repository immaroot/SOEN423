package Corba.CampusServerApp;


/**
* Corba/CampusServerApp/TimeHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 7:27:23 o'clock PM EDT
*/

abstract public class TimeHelper
{
  private static String  _id = "IDL:Corba/CampusServerApp/Time:1.0";

  public static void insert (org.omg.CORBA.Any a, Corba.CampusServerApp.Time that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Corba.CampusServerApp.Time extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
          _members0[0] = new org.omg.CORBA.StructMember (
            "hour",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
          _members0[1] = new org.omg.CORBA.StructMember (
            "minute",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (Corba.CampusServerApp.TimeHelper.id (), "Time", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Corba.CampusServerApp.Time read (org.omg.CORBA.portable.InputStream istream)
  {
    Corba.CampusServerApp.Time value = new Corba.CampusServerApp.Time ();
    value.hour = istream.read_short ();
    value.minute = istream.read_short ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Corba.CampusServerApp.Time value)
  {
    ostream.write_short (value.hour);
    ostream.write_short (value.minute);
  }

}