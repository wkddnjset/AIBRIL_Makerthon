package com.wf.smapp.opencv;

import org.opencv.core.Core;


import org.opencv.core.Mat;

import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;


//@Component
public class FaceDetection{

	// a timer for acquiring the video stream
	private Timer timer;
	private VideoCapture capture;
	private boolean cameraActive;
	private CascadeClassifier faceCascade;
    // minimum face size
    private int absoluteFaceSize;

	public FaceDetection(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		this.capture = new VideoCapture();
		this.faceCascade = new CascadeClassifier();

		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:haarcascade_frontalface_alt.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("haarcascade_frontalface_alt path:"+file.getAbsolutePath());
		if (this.faceCascade.load(file.getAbsolutePath())){
			System.out.println("Loaded haarcascade_frontalface_alt");
		}else{
			System.out.println("Not Loaded haarcascade_frontalface_alt");
		}
		this.absoluteFaceSize = 0;
		
		startCamera();
	}
	
	protected void startCamera()
	{
		if (!this.cameraActive)
		{
			// start the video capture
			this.capture.open(0);
			
			// is the video stream available?
			if (this.capture.isOpened())
			{
				this.cameraActive = true;
				
				// grab a frame every 33 ms (30 frames/sec)
				TimerTask frameGrabber = new TimerTask() {
					@Override
					public void run(){
						grabFrame();
					}
				};
				this.timer = new Timer();
				this.timer.schedule(frameGrabber, 0, 5);
				
				// update the button content
//				this.cameraButton.setText("Stop Camera");
				System.out.println("capture.isOpened()");
			}
			else
			{
				// log the error
				System.err.println("Impossible to open the camera connection...");
			}
		}
		else
		{
			// the camera is not active at this point
			this.cameraActive = false;
			// update again the button content
//			this.cameraButton.setText("Start Camera");
			System.out.println("Start Camera");
			// stop the timer
			if (this.timer != null)
			{
				this.timer.cancel();
				this.timer = null;
			}
			// release the camera
			this.capture.release();

		}
	}
	
	private void grabFrame()
	{
		// init everything
		Mat frame = new Mat();
		
		// check if the capture is open
		if (this.capture.isOpened())
		{
			try
			{
				// read the current frame
				this.capture.read(frame);
				
				// if the frame is not empty, process it
				if (!frame.empty())
				{
					// show the chessboard pattern
//					this.findAndDrawPoints(frame);
					
					this.detectAndDisplay(frame);
				}
				
			}
			catch (Exception e)
			{
				// log the (full) error
				System.err.print("ERROR");
				e.printStackTrace();
			}
		}
		
	}
	
	private void detectAndDisplay(Mat frame)
	{
		// init
		MatOfRect faces = new MatOfRect();
		Mat grayFrame = new Mat();

		// convert the frame in gray scale
		Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
		// equalize the frame histogram to improve the result
		Imgproc.equalizeHist(grayFrame, grayFrame);

		// compute minimum face size (20% of the frame height)
		if (this.absoluteFaceSize == 0)
		{
			int height = grayFrame.rows();
			if (Math.round(height * 0.2f) > 0)
			{
				this.absoluteFaceSize = Math.round(height * 0.2f);
			}
		}

		// detect faces
		this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE, new Size(
				this.absoluteFaceSize, this.absoluteFaceSize), new Size());

		// each rectangle in faces is a face
		Rect[] facesArray = faces.toArray();
		for (int i = 0; i < facesArray.length; i++){
//			Core.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0, 255), 3);
			System.out.println("detect face!!");
		}

	}

	 /**
     * Convert a Mat object (OpenCV) in the corresponding Image for JavaFX
     *
     * @param frame
     *            the {@link Mat} representing the current frame
     * @return the {@link Image} to show
     */
 /*   private Image mat2Image(Mat frame)
    {
            // create a temporary buffer
            MatOfByte buffer = new MatOfByte();
            // encode the frame in the buffer, according to the PNG format
            Highgui.imencode(".png", frame, buffer);
            // build and return an Image created from the image encoded in the
            // buffer
            return new Image(new ByteArrayInputStream(buffer.toArray()));
    }*/		
}
