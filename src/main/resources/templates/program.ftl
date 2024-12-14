<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Programs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div id="header" class="container mt-4">
    <h2 class="text-center">Manage Programs</h2>
</div>

<div id="content" class="container mt-5">
    <fieldset class="border p-4 rounded">
        <legend class="w-auto">Add / Edit Program</legend>
        <form name="program" action="/programs/save" method="post" class="row g-3">
            <input type="hidden" name="id" value="${program.id!}" />
            <div class="col-md-6">
                <label for="name" class="form-label">Name</label>
                <input type="text" id="name" name="name" class="form-control" value="${program.name!}" />
            </div>
            <div class="col-md-3">
                <div class="form-check mt-4">
                    <input type="checkbox" id="pause" name="pause" class="form-check-input" <#if program.pause?? && program.pause>checked</#if> />
                    <label for="pause" class="form-check-label">Pause</label>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-check mt-4">
                    <input type="checkbox" id="work" name="work" class="form-check-input" <#if program.work?? && program.work>checked</#if> />
                    <label for="work" class="form-check-label">Work</label>
                </div>
            </div>

            <div class="col-12 mt-4">
                <h5>Steps</h5>
                <table id="stepsTable" class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Step ID</th>
                        <th>Date Start</th>
                        <th>Date End</th>
                        <th>Done</th>
                        <th>Temp</th>
                        <th>Time</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if program.steps??>
                        <#list program.steps as step>
                            <tr>
                                <td><input type="text" name="steps[${step_index}].id" class="form-control" value="${step.id}" /></td>
                                <td><input type="datetime-local" name="steps[${step_index}].dateStart" class="form-control" value="${step.dateStart?string("yyyy-MM-dd'T'HH:mm")}" /></td>
                                <td><input type="datetime-local" name="steps[${step_index}].dateEnd" class="form-control" value="${step.dateEnd?string("yyyy-MM-dd'T'HH:mm")}" /></td>
                                <td><input type="checkbox" name="steps[${step_index}].done" class="form-check-input" <#if step.done>checked</#if> /></td>
                                <td><input type="number" step="0.1" name="steps[${step_index}].temp" class="form-control" value="${step.temp}" /></td>
                                <td><input type="text" name="steps[${step_index}].time" class="form-control" value="${step.time}" /></td>
                                <td>
                                    <button type="button" class="btn btn-danger btn-sm" onclick="removeStep(this)">Remove</button>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
                <button type="button" class="btn btn-primary btn-sm" onclick="addStep()">Add Step</button>
            </div>

            <div class="col-12">
                <button type="submit" class="btn btn-success">Save Program</button>
            </div>
        </form>
    </fieldset>

    <table class="table table-striped table-bordered mt-4">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Pause</th>
            <th>Work</th>
            <th>Steps</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list Programs as program>
            <tr>
                <td>${program.id}</td>
                <td>${program.name}</td>
                <td>${program.pause?string('true', 'false')}</td>
                <td>${program.work?string('true', 'false')}</td>
                <td>
                    <table class="table table-sm">
                        <thead>
                        <tr>
                            <th>Step ID</th>
                            <th>Date Start</th>
                            <th>Date End</th>
                            <th>Done</th>
                            <th>Temp</th>
                            <th>Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list program.steps as step>
                            <tr>
                                <td>${step.id}</td>
                                <td>${step.dateStart}</td>
                                <td>${step.dateEnd}</td>
                                <td>${step.done?string('Yes', 'No')}</td>
                                <td>${step.temp}</td>
                                <td>${step.time}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </td>
                <td>
                    <form action="/programs/edit/${program.id}" method="get" style="display: inline;">
                        <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                    </form>
                    <form action="/programs/delete/${program.id}" method="post" style="display: inline;">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</div>

<script>
    function addStep() {
        const stepsTable = document.getElementById('stepsTable').getElementsByTagName('tbody')[0];
        const rowCount = stepsTable.rows.length;
        const newRow = stepsTable.insertRow();

        newRow.innerHTML = `
            <td><input type="text" name="steps[${rowCount}].id" class="form-control" /></td>
            <td><input type="datetime-local" name="steps[${rowCount}].dateStart" class="form-control" /></td>
            <td><input type="datetime-local" name="steps[${rowCount}].dateEnd" class="form-control" /></td>
            <td><input type="checkbox" name="steps[${rowCount}].done" class="form-check-input" /></td>
            <td><input type="number" step="0.1" name="steps[${rowCount}].temp" class="form-control" /></td>
            <td><input type="text" name="steps[${rowCount}].time" class="form-control" /></td>
            <td><button type="button" class="btn btn-danger btn-sm" onclick="removeStep(this)">Remove</button></td>
        `;
    }

    function removeStep(button) {
        const row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
    }
</script>
</body>
</html>