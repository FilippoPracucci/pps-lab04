package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import u03.extensionmethods.Optionals.*
import u03.extensionmethods.Sequences.*
import Sequence.*

class SchoolModelTest:

  val schoolModel: SchoolModule = BasicSchoolModule
  import schoolModel.*

  val school = emptySchool

  @Test def testCourses() =
    val school = schoolModel.emptySchool
    assertEquals(Nil(), school.courses)

  @Test def testTeachers() =
    val school = schoolModel.emptySchool
    assertEquals(Nil(), school.teachers)

  @Test def testSetTeacherToCourse() =
    val john = teacher("John")
    val math = course("Math")
    val italian = course("Italian")
    val school2 = school.setTeacherToCourse(john, math)
    assertEquals((john, math, Cons((john, math), Nil())), school2)