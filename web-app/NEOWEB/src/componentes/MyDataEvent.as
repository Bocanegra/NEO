package componentes
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;

        
	public class MyDataEvent extends Event
	{

	    private var _result:XML;
	    public static const CARGA_TERMINADA:String = "MyDataEvent.CARGA_TERMINADA";
	    public static const USUARIO_AUTORIZADO:String = "MyDataEvent.USUARIO_AUTORIZADO";
	    public static const CAMBIO_ESTADO_TAREA:String = "MyDataEvent.CAMBIO_ESTADO_TAREA";
	    public static const CONTACTOS_ACTUALIZADOS:String = "MyDataEvent.CONTACTOS_ACTUALIZADOS";
	    public static const TRY_LOGIN:String = "MyDataEvent.TRY_LOGIN";
	    public static const MENU_SELECTION:String = "MyDataEvent.MENU_SELECTION";
	    public static const JOIN_NEO:String = "MyDataEvent.JOIN_NEO";
	    public static const END_REGISTRATION:String = "MyDataEvent.END_REGISTRATION";


		public function MyDataEvent(type:String, argResult:XML=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
		    this._result = argResult;
			super(type, bubbles, cancelable);
		}
        public function get result():XML
        {
            return this._result;
        }        
        override public function clone():Event
        {
            return new MyDataEvent(type, result, bubbles, cancelable);
        }
        		
	}
}