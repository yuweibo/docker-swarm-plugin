/*
 * The MIT License
 *
 *  Copyright (c) 2015, CloudBees, Inc.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 */

package suryagaddipati.jenkinsdockerslaves;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Statistics;
import com.google.common.collect.Iterables;
import hudson.model.AbstractProject;
import hudson.model.Executor;
import hudson.model.Job;
import hudson.model.Queue;
import hudson.model.Run;
import hudson.slaves.AbstractCloudComputer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DockerComputer extends AbstractCloudComputer<DockerSlave> {

    private final Job job;
    private String containerId;
    private String volumeName;


    private String swarmNodeName;


    public DockerComputer(DockerSlave dockerSlave, Job job) {
        super(dockerSlave);
        this.job = job;
    }


    @Override
    public void recordTermination() {
        if (isAcceptingTasks()) {
            super.recordTermination();
        }
    }

//
//<<<<<<< HEAD
//    private void cleanupDockerVolumeAndContainer() {
//        DockerSlaveConfiguration configuration = DockerSlaveConfiguration.get();
//        try( DockerClient dockerClient = configuration.newDockerClient()){
//            try{
//                if (containerId != null){
//                    Queue.Executable currentExecutable = getExecutors().get(0).getCurrentExecutable();
//                    LOGGER.info("Getting Stats for " + currentExecutable);
//                    if(currentExecutable instanceof Run && ((Run)currentExecutable).getAction(DockerSlaveInfo.class) != null){
//                        Run run = ((Run) currentExecutable);
//                        DockerSlaveInfo slaveInfo = ((Run) currentExecutable).getAction(DockerSlaveInfo.class);
//                        Statistics stats = dockerClient.statsCmd(containerId).exec();
//                        slaveInfo.setStats(stats);
//                        if(slaveInfo.wasThrottled()){
//
//                        }
////                        run.getParent().addAction(new JobRuntimeStatsAction(slaveInfo));
//                        run.save();
//                    }
//                    dockerClient.killContainerCmd(containerId).exec();
//                    dockerClient.removeContainerCmd(containerId).exec();
//                }
//                if(volumeName != null){
//                    dockerClient.removeVolumeCmd(volumeName).exec();
//                }
//            }catch (Exception e){
//                LOGGER.log(Level.INFO,"failed to cleanup comtainer "+ containerId, e);
//            }
//        } catch (IOException e) {
//            LOGGER.log(Level.INFO,"Failed to close connection to docker client"+ containerId, e);
//        }
//    }
//=======
//>>>>>>> master


    public AbstractProject getJob() {
        return (AbstractProject) job;
    }



    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public void setNodeName(String nodeName) {
        this.swarmNodeName = nodeName;
    }
    public String getSwarmNodeName() {
        return swarmNodeName;
    }

    public Queue.Executable getCurrentBuild(){
        if (!Iterables.isEmpty(getExecutors())){
            Executor exec = getExecutors().get(0);
            return exec.getCurrentExecutable()==null? null: exec.getCurrentExecutable();
        }
        return null;
    }

    public String getContainerId() {
        return containerId;
    }

    public String getVolumeName() {
        return volumeName;
    }
}
