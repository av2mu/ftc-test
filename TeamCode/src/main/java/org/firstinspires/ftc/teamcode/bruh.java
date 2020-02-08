package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="basic mechanum drive: Iterative OpMode", group="Iterative Opmode")
public class bruh extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightRear = null;
    private DcMotor turretHeight = null;
    private CRServo pinch = null;





    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFront  = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftRear = hardwareMap.get(DcMotor.class, "left_rear");
        rightRear = hardwareMap.get(DcMotor.class, "right_rear");
        turretHeight = hardwareMap.get(DcMotor.class, "turret_height");
        pinch = hardwareMap.get(CRServo.class, "wrist");
        //leftFront = Range.clip(leftFront,0.5,-0.5);
        //rightFront = Range.clip(rightFront,1,-1);
        leftFront.setPower(0.0);
        rightFront.setPower(0.0);
        leftRear.setPower(0.0);
        rightRear.setPower(0.0);
        turretHeight.setPower(0.0);
        pinch.setPower(0.0);



        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry

        double magnitude = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_trigger-gamepad1.left_trigger;
        double turretY = -((gamepad1.right_stick_y));


        final double fld = (magnitude * Math.cos(robotAngle) + rightX);
        final double frd = -(magnitude * Math.sin(robotAngle) - rightX);
        final double bld = (magnitude * Math.sin(robotAngle) + rightX);
        final double brd = -(magnitude * Math.cos(robotAngle) - rightX);

        leftFront.setPower(fld);
        rightFront.setPower(frd);
        leftRear.setPower(bld);
        rightRear.setPower(brd);

        turretHeight.setPower(turretY);

        if (gamepad1.left_bumper)

            pinch.setPower(.2);
        else
            pinch.setPower(0);


    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}