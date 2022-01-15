public class RepeatingTask extends Thread
{
    private final int delay;
    private TaskInformation taskInfo;

    private int timer = 0;

    public RepeatingTask(int delay, TaskInformation taskInfo)
    {
        this.taskInfo = taskInfo;
        this.delay = delay;
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(taskInfo.shouldSkipTimer())
            {
                timer = delay;
            }

            if(timer == delay)
            {
                taskInfo.runTask();
                timer = 0;
            }

            try
            {
                Thread.sleep(1);
                timer++;
            }
            catch(Exception ex)
            {
                ex.printStackTrace( );
            }
        }
    }
}
