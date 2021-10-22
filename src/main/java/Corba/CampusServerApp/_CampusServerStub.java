package CampusServerApp;


/**
* CampusServerApp/_CampusServerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CampusServer.idl
* Friday, October 22, 2021 3:37:47 o'clock PM EDT
*/

public class _CampusServerStub extends org.omg.CORBA.portable.ObjectImpl implements CampusServerApp.CampusServer
{

  public String getAvailableTimeSlot (CampusServerApp.Date date, String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getAvailableTimeSlot", true);
                CampusServerApp.DateHelper.write ($out, date);
                $out.write_string (id);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getAvailableTimeSlot (date, id        );
            } finally {
                _releaseReply ($in);
            }
  } // getAvailableTimeSlot

  public CampusServerApp.RoomRecord bookRoom (CampusServerApp.Campus campusName, short roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot timeSlot, String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("bookRoom", true);
                CampusServerApp.CampusHelper.write ($out, campusName);
                $out.write_short (roomNumber);
                CampusServerApp.DateHelper.write ($out, date);
                CampusServerApp.TimeSlotHelper.write ($out, timeSlot);
                $out.write_string (id);
                $in = _invoke ($out);
                CampusServerApp.RoomRecord $result = CampusServerApp.RoomRecordHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return bookRoom (campusName, roomNumber, date, timeSlot, id        );
            } finally {
                _releaseReply ($in);
            }
  } // bookRoom

  public CampusServerApp.RoomRecord cancelBooking (String bookingID, String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("cancelBooking", true);
                $out.write_string (bookingID);
                $out.write_string (id);
                $in = _invoke ($out);
                CampusServerApp.RoomRecord $result = CampusServerApp.RoomRecordHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return cancelBooking (bookingID, id        );
            } finally {
                _releaseReply ($in);
            }
  } // cancelBooking

  public String createRoom (short roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot[] timeSlotSet, String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createRoom", true);
                $out.write_short (roomNumber);
                CampusServerApp.DateHelper.write ($out, date);
                CampusServerApp.TimeSlotSetHelper.write ($out, timeSlotSet);
                $out.write_string (id);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createRoom (roomNumber, date, timeSlotSet, id        );
            } finally {
                _releaseReply ($in);
            }
  } // createRoom

  public String deleteRoom (short roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot[] timeSlotSet, String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteRoom", true);
                $out.write_short (roomNumber);
                CampusServerApp.DateHelper.write ($out, date);
                CampusServerApp.TimeSlotSetHelper.write ($out, timeSlotSet);
                $out.write_string (id);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return deleteRoom (roomNumber, date, timeSlotSet, id        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteRoom

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CampusServerApp/CampusServer:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _CampusServerStub
