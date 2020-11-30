pipeline {
         agent any
         environment {
             env_var = 'demo_env_var'

         }
         stages {
                 stage('First stage') {
                 steps {
                 if (env.GERRIT_PATCHSET_REVISION){
                     sh """
                            echo 'This is first stage of Jenkins.'
                            echo $pwd
                        """
                    }
                    else{
                         ehco "triggered by user"
                    }
                        }
                    }
                stage('Second stage') {
                 steps {
                    input('Do you want to proceed?')
                    sh """
                       echo "this is shell script part for script"
                      """
                 }
                 }
                 stage('Parallel stage') {
                 parallel {
                            stage('First parallel stage ') {
                           steps {
                                echo "First parallel stage starts here"
                                Parallelstagefunc()
                           }
                           }
                            stage('second parallel stage') {

                              steps {
                                echo "parallel state is over"
                                sh '''
                                    echo "this is a text file" >test.txt
                                    cat test.txt
                                  '''
                                
                              }
                           }
                           }
                           }
                stage('Last stage') {
                    steps {
                     sh """
                            echo 'This is last stages.'
                            echo $env_var
                        """
                     
                        }
                    }
                 
                }
post {
        always {
          test.txt', onlyIfSuccessful: true
            }
  
  }
}
def Parallelstagefunc() {
    
    println "this is function for parallel stage"
        sh '''
            echo $WORKSPACE
            echo "this is shell script part for parallel stage"

        '''
}



pipeline {
         agent any
         environment {
             env_var = 'demo_env_var'
             def caused=env.getBuildCause('jenkins.branch.BranchEventCause')
         }
         stages {
                 stage('First stage') {
                 steps {
                 
                 if (env.GERRIT_PATCHSET_REVISION){
                     sh """
                            echo 'This is first stage of Jenkins.'
                            echo $pwd
                        """
                    }
                    else{
                         ehco "triggered by user"
                    }
                    
                        }
                    }
                stage('Second stage') {
                 steps {
                    input('Do you want to proceed?')
                    sh """
                       echo "this is shell script part for script"
                      """
                 }
                 }
                 stage('Parallel stage') {
                 parallel {
                            stage('First parallel stage ') {
                           steps {
                                echo "First parallel stage starts here"
                                Parallelstagefunc()
                           }
                           }
                            stage('second parallel stage') {

                              steps {
                                echo "parallel state is over"
                                sh '''
                                    echo "this is a text file" >test.txt
                                    cat test.txt
                                  '''
                                
                              }
                           }
                           }
                           }
                stage('Last stage') {
                    steps {
                     sh """
                            echo 'This is last stages.'
                            echo $env_var
                        """
                     
                        }
                    }
                 
                }
            post {
                    always {
                            archiveArtifacts artifacts:'test.txt', onlyIfSuccessful: true
                    }
  
  }
}
def Parallelstagefunc() {
    
    println "this is function for parallel stage"
        sh '''
            echo $WORKSPACE
            echo "this is shell script part for parallel stage"

        '''
}
