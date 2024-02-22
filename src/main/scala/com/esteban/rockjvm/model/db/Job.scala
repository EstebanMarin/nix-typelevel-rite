package com.esteban.rockjvm.model.db

import java.time.Instant
import java.util.UUID

import doobie.*
import doobie.implicits.*

object Job:
  final case class JobInfo(
    id: UUID,
    name: String,
    description: String,
    status: JobStatus,
    createdAt: Instant,
    updatedAt: Instant,
  )
  object JobInfo:
    given Meta[JobInfo] =
      val instant = Instant.now() match
        case instant: Instant => instant
        case null => throw new IllegalStateException("Instant.now() returned null")

      Meta[UUID].timap(id =>
        JobInfo(id, "name", "description", JobStatus.Available, instant, instant)
      )(jobInfo => jobInfo.id)

val uuidFunction: String => UUID = str =>
  UUID.fromString(str) match
    case uuid: UUID => uuid
    case null => throw new IllegalArgumentException(s"Invalid UUID string: $str")

given Meta[UUID] =
  Meta[String].imap[UUID](uuidFunction)(_.toString)

enum JobStatus:
  case Available, Closed, Inprogress

object JobStatus:
  given Meta[JobStatus] =
    Meta[String].imap[JobStatus](JobStatus.valueOf)(_.toString)
