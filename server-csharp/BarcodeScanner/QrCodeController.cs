using System.Web.Http;
using WindowsInput;
using WindowsInput.Native;

namespace BarcodeScanner
{
    public class QrCodeController : ApiController
    {

        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("api/QrCode/SendQrCodeData")]
        // POST api/values 
        public IHttpActionResult Post(PostData value)
        {
            var simulator = new InputSimulator();
            simulator.Keyboard.TextEntry(value.Text);
            simulator.Keyboard.KeyPress(VirtualKeyCode.RETURN);
            return Ok(new ResultData(){Text = "OK"});
        }
    }
}
