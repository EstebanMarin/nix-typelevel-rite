package com.esteban.rockjvm.model.db

import java.time.Instant
import java.util.UUID

object Job:
  final case class JobInfo(
    id: UUID,
    name: String,
    description: String,
    status: JobStatus,
    createdAt: Instant,
    updatedAt: Instant,
  )

  enum JobStatus:
    case Available, Closed, Inprogress

  val emptyJobInfo: JobInfo = JobInfo(
    id = UUID.randomUUID(),
    name = "",
    description = "",
    status = JobStatus.Available,
    createdAt = Instant.now(),
    updatedAt = Instant.now(),
  )
