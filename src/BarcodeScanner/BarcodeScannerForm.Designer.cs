namespace BarcodeScanner
{
    partial class BarcodeScannerForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(BarcodeScannerForm));
            this.QrCodePictureBox = new System.Windows.Forms.PictureBox();
            this.btnHide = new System.Windows.Forms.Button();
            this.tblStatus = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.numPort = new System.Windows.Forms.NumericUpDown();
            ((System.ComponentModel.ISupportInitialize)(this.QrCodePictureBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numPort)).BeginInit();
            this.SuspendLayout();
            // 
            // QrCodePictureBox
            // 
            this.QrCodePictureBox.Dock = System.Windows.Forms.DockStyle.Top;
            this.QrCodePictureBox.Location = new System.Drawing.Point(0, 0);
            this.QrCodePictureBox.Name = "QrCodePictureBox";
            this.QrCodePictureBox.Size = new System.Drawing.Size(470, 470);
            this.QrCodePictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.QrCodePictureBox.TabIndex = 0;
            this.QrCodePictureBox.TabStop = false;
            // 
            // btnHide
            // 
            this.btnHide.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.btnHide.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnHide.Location = new System.Drawing.Point(377, 499);
            this.btnHide.Name = "btnHide";
            this.btnHide.Size = new System.Drawing.Size(81, 23);
            this.btnHide.TabIndex = 1;
            this.btnHide.Text = "Hide";
            this.btnHide.UseVisualStyleBackColor = true;
            this.btnHide.Click += new System.EventHandler(this.btnHide_Click);
            // 
            // tblStatus
            // 
            this.tblStatus.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.tblStatus.AutoSize = true;
            this.tblStatus.Location = new System.Drawing.Point(458, 476);
            this.tblStatus.Name = "tblStatus";
            this.tblStatus.Size = new System.Drawing.Size(0, 13);
            this.tblStatus.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(7, 479);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(26, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Port";
            // 
            // numPort
            // 
            this.numPort.Location = new System.Drawing.Point(40, 477);
            this.numPort.Maximum = new decimal(new int[] {
            65535,
            0,
            0,
            0});
            this.numPort.Name = "numPort";
            this.numPort.Size = new System.Drawing.Size(71, 20);
            this.numPort.TabIndex = 5;
            this.numPort.Value = new decimal(new int[] {
            7789,
            0,
            0,
            0});
            this.numPort.ValueChanged += new System.EventHandler(this.numPort_ValueChanged);
            // 
            // BarcodeScannerForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(470, 534);
            this.Controls.Add(this.numPort);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.tblStatus);
            this.Controls.Add(this.btnHide);
            this.Controls.Add(this.QrCodePictureBox);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "BarcodeScannerForm";
            this.Text = "Barcode Scanner ";
            this.Load += new System.EventHandler(this.BarcodeScannerForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.QrCodePictureBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numPort)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox QrCodePictureBox;
        private System.Windows.Forms.Button btnHide;
        private System.Windows.Forms.Label tblStatus;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.NumericUpDown numPort;
    }
}