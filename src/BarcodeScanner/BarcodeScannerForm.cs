using QRCoder;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Net;
using System.Net.Http;
using System.Net.Sockets;
using System.Windows.Forms;
using Microsoft.Owin.Hosting;

namespace BarcodeScanner
{
    public partial class BarcodeScannerForm : Form
    {
        private NotifyIcon notifyIcon=new NotifyIcon();
        private string ip;
        private IDisposable server;

        public BarcodeScannerForm()
        {
            InitializeComponent();
        }
         

        private void BarcodeScannerForm_Load(object sender, EventArgs e)
        {

            notifyIcon.Icon = Resources.appIcon;
            notifyIcon.Click += NotifyIcon_Click;

            ip = $"http://{GetLocalIpAddress()}:{numPort.Value}/";
            var qrCodeImage = QrCodeImage(ip);
            QrCodePictureBox.Image = qrCodeImage;

            StartService();
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            server.Dispose();
        }

        private void StartService()
        {
            server = WebApp.Start<OwinStartup>(ip);
        }

        private void NotifyIcon_Click(object sender, EventArgs e)
        {
            this.Show();
            this.WindowState = FormWindowState.Normal;
        }

        private static Bitmap QrCodeImage(string qrData)
        {
           
            var qrGenerator = new QRCodeGenerator();
            var qrCodeData = qrGenerator.CreateQrCode(qrData, QRCodeGenerator.ECCLevel.H);
            var qrCode = new QRCode(qrCodeData);
           return qrCode.GetGraphic(20);
        }

        public static string GetLocalIpAddress()
        {
            var host = Dns.GetHostEntry(Dns.GetHostName());
            foreach (var ip in host.AddressList)
            {
                if (ip.AddressFamily == AddressFamily.InterNetwork)
                {
                    return ip.ToString();
                }
            }
            throw new Exception("No network adapters with an IPv4 address in the system!");
        }

        private void btnHide_Click(object sender, EventArgs e)
        {
             notifyIcon.BalloonTipTitle = "Minimize to Tray App";
            notifyIcon.BalloonTipText = "Barcode Scanner running in backgrounds.";

            if (FormWindowState.Normal == this.WindowState)
            {
                notifyIcon.Visible = true;
                notifyIcon.ShowBalloonTip(500);
                this.Hide();
            }
            else if (FormWindowState.Normal == this.WindowState)
            {
                notifyIcon.Visible = false;
            }
        }

        private void numPort_ValueChanged(object sender, EventArgs e)
        {

        }
    }
}
