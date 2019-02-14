using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Http;
using WindowsInput;

namespace BarcodeScanner
{
    public class QrCodeController : ApiController
    {

        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("api/QrCode/SendQrCodeData")]
        // POST api/values 
        public void Post(PostData value)
        {
            var simulator = new InputSimulator();
            simulator.Keyboard.TextEntry(value.Text);
        }
    }
}
