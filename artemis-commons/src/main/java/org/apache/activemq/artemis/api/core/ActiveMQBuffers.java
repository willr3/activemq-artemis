/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.artemis.api.core;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.apache.activemq.artemis.core.buffers.impl.ChannelBufferWrapper;

/**
 * Factory class to create instances of {@link ActiveMQBuffer}.
 */
public final class ActiveMQBuffers {

   public static class AMQBuffer extends ChannelBufferWrapper{

      public AMQBuffer(final ByteBuf buffer){
         super(buffer,true);//always releasable
      }
      public AMQBuffer(final ByteBuf buffer,boolean bool){
         super(buffer,bool);//always releasable
      }

      @Override
      public ActiveMQBuffer copy() {
         return new AMQBuffer(buffer.copy(), releasable);
      }
      @Override
      public ActiveMQBuffer copy(final int index, final int length) {
         return new AMQBuffer(buffer.copy(index, length), releasable);
      }
      @Override
      public ActiveMQBuffer duplicate() {
         return new AMQBuffer(buffer.duplicate(), releasable);
      }
      @Override
      public ActiveMQBuffer readBytes(final int length) {
         return new AMQBuffer(buffer.readBytes(length), releasable);
      }
      @Override
      public ActiveMQBuffer readSlice(final int length) {
         return new AMQBuffer(buffer.readSlice(length), releasable);
      }
      @Override
      public ActiveMQBuffer slice() {
         return new AMQBuffer(buffer.slice(), releasable);
      }
      @Override
      public ActiveMQBuffer slice(final int index, final int length) {
         return new AMQBuffer(buffer.slice(index, length), releasable);
      }
      @Override
      public void retain(){
         buffer.retain();
      }
      @Override
      public void release(){
         //buffer.capacity();
         buffer.release();

      }
   }


   private static final PooledByteBufAllocator POOLED_BYTE_BUF_ALLOCATOR = new PooledByteBufAllocator();


   public static ActiveMQBuffer pooledDynamicBuffer(final int size){
      return new AMQBuffer(POOLED_BYTE_BUF_ALLOCATOR.buffer(size));
      //return new AMQBuffer(Unpooled.buffer(size));
   }

   /**
    * Creates a <em>self-expanding</em> ActiveMQBuffer with the given initial size
    *
    * @param size the initial size of the created ActiveMQBuffer
    * @return a self-expanding ActiveMQBuffer starting with the given size
    */
   public static ActiveMQBuffer dynamicBuffer(final int size) {
      return new ChannelBufferWrapper(Unpooled.buffer(size));
   }

   /**
    * Creates a <em>self-expanding</em> ActiveMQBuffer filled with the given byte array
    *
    * @param bytes the created buffer will be initially filled with this byte array
    * @return a self-expanding ActiveMQBuffer filled with the given byte array
    */
   public static ActiveMQBuffer dynamicBuffer(final byte[] bytes) {
      ActiveMQBuffer buff = dynamicBuffer(bytes.length);

      buff.writeBytes(bytes);

      return buff;
   }

   /**
    * Creates an ActiveMQBuffer wrapping an underlying NIO ByteBuffer
    *
    * The position on this buffer won't affect the position on the inner buffer
    *
    * @param underlying the underlying NIO ByteBuffer
    * @return an ActiveMQBuffer wrapping the underlying NIO ByteBuffer
    */
   public static ActiveMQBuffer wrappedBuffer(final ByteBuffer underlying) {
      ActiveMQBuffer buff = new ChannelBufferWrapper(Unpooled.wrappedBuffer(underlying));

      buff.clear();

      return buff;
   }

   /**
    * Creates an ActiveMQBuffer wrapping an underlying byte array
    *
    * @param underlying the underlying byte array
    * @return an ActiveMQBuffer wrapping the underlying byte array
    */
   public static ActiveMQBuffer wrappedBuffer(final byte[] underlying) {
      return new ChannelBufferWrapper(Unpooled.wrappedBuffer(underlying));
   }

   /**
    * Creates a <em>fixed</em> ActiveMQBuffer of the given size
    *
    * @param size the size of the created ActiveMQBuffer
    * @return a fixed ActiveMQBuffer with the given size
    */
   public static ActiveMQBuffer fixedBuffer(final int size) {
      return new ChannelBufferWrapper(Unpooled.buffer(size, size));
   }

   private ActiveMQBuffers() {
      // Utility class
   }
}
