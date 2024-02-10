package com.esteban.rockjvm.model.db

import java.time.Instant
import java.util.UUID

final case class Job(
  id: UUID,
  name: String,
  description: String,
  status: JobStatus,
  createdAt: Instant,
  updatedAt: Instant,
)
