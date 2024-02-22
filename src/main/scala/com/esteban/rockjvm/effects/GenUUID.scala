package com.esteban.rockjvm.effects

import java.util.*

import cats.*
import cats.effect.Sync

trait GenUUID[F[_]]:
  def generate: F[UUID]
  def read(str: String): F[UUID]

object GenUUID:
  def apply[F[_]: GenUUID: MonadThrow]: GenUUID[F] = implicitly

  implicit def forSync[F[_]: Sync]: GenUUID[F] =
    new GenUUID[F]:
      override def generate: F[UUID] = Sync[F].delay(UUID.randomUUID() match
        case uuid: UUID => uuid
        case _ => throw new IllegalStateException("UUID.randomUUID() returned null")
      )

      override def read(str: String): F[UUID] =
        ApplicativeThrow[F].catchNonFatal(UUID.fromString(str) match
          case uuid: UUID => uuid
          case _ => throw new IllegalArgumentException(s"Invalid UUID string: $str")
        )
