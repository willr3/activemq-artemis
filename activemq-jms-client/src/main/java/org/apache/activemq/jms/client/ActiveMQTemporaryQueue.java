/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.apache.activemq.jms.client;

import javax.jms.TemporaryQueue;


/**
 * ActiveMQ implementation of a JMS TemporaryQueue.
 * <br>
 * This class can be instantiated directly.
 *
 * @author <a href="mailto:tim.fox@jboss.com">Tim Fox</a>
 * @version <tt>$Revision: 3569 $</tt>
 *
 */
public class ActiveMQTemporaryQueue extends ActiveMQQueue implements TemporaryQueue
{
   // Constants -----------------------------------------------------

   private static final long serialVersionUID = -4624930377557954624L;

   // Static --------------------------------------------------------

   // Attributes ----------------------------------------------------

   // Constructors --------------------------------------------------


   // TemporaryQueue implementation ------------------------------------------

   // Public --------------------------------------------------------

   /**
    * @param address
    * @param name
    * @param session
    */
   public ActiveMQTemporaryQueue(String address, String name, ActiveMQSession session)
   {
      super(address, name, true, session);
   }

   @Override
   public String toString()
   {
      return "ActiveMQTemporaryQueue[" + name + "]";
   }

   // Package protected ---------------------------------------------

   // Protected -----------------------------------------------------

   // Private -------------------------------------------------------

   // Inner classes -------------------------------------------------
}